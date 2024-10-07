const fs = require('fs');
const path = require('path');
const PDFDocument = require('pdfkit');
const stripe = require('stripe')('sk_test_dcVHL86Zd2TjE8fOwFkcQO4e00JGCZhQw5');
const ProductModel = require('../models/product.model');
const CartModel = require('../models/cart.model');
const CartItemModel = require('../models/cart-item.model');
const Order = require('../models/order.model');
const { checkEmptyArray } = require('../utils/array.utils');

const ITEMS_PER_PAGE = 2;

exports.getProducts = (req, res, next) => {
    const page = +req.query.page || 1; // (|| 1) handles the default value when there isn't query parameters
    let totalItems;

    Product.find()
        .countDocuments()
        .then(numProducts => {
            totalItems = numProducts;
            return Product.find()
                .skip((page - 1) * ITEMS_PER_PAGE)
                .limit(ITEMS_PER_PAGE)
        })
        .then(products => {
            res.render('shop/product-list', {
                prods: products,
                pageTitle: 'All Products',
                path: '/products',
                currentPage: page,
                hasNextPage: ITEMS_PER_PAGE * page < totalItems,
                hasPreviousPage: page > 1,
                nextPage: page + 1,
                previousPage: page - 1,
                lastPage: Math.ceil(totalItems / ITEMS_PER_PAGE)
            });
        })
        .catch(err => {
            const error = new Error(err);
            error.httpStatusCode = 500;
            return next(error);
        });
};

exports.getProduct = (req, res, next) => {
    const prodId = req.params.productId;
    // findById() can accept a string and Mongoose will
    // automatically convert it to an Object Id
    Product.findById(prodId)
        .then(product => {
            res.render('shop/product-detail', {
                product: product,
                pageTitle: product.title,
                path: '/products'
            });
        })
        .catch(err => {
            const error = new Error(err);
            error.httpStatusCode = 500;
            return next(error);
        });
};

exports.postCart = (req, res, next) => {
    const { id } = req.currentUser;
    const { products } = req.body;

    CartModel.create({ created_by: id, status: 1 })
        .then(async (idCart) => {
            if (checkEmptyArray(products)) {
                for (item of products) {
                    const { product_id } = item;
                    const product = await ProductModel.findOne({ id: product_id })
                    await CartItemModel.create({
                        cart_id: idCart,
                        product_id,
                        quantity: 1,
                        price: product.price
                    })
                }
            }
            return res.json({
                id: idCart
            });
        })
        .catch(err => {
            return res.json({
                err: err
            });
        });
};

exports.updateCart = (req, res, next) => {
    // const { id } = req.currentUser;
    const { products, cart_id } = req.body;

    CartItemModel.delete(cart_id)
        .then(async () => {
            if (checkEmptyArray(products)) {
                for (item of products) {
                    const { product_id } = item;
                    const product = await ProductModel.findOne({ id: product_id })
                    await CartItemModel.create({
                        cart_id: cart_id,
                        product_id,
                        quantity: 1,
                        price: product.price
                    })
                }
            }
            return res.json({
                id: idCart
            });
        })
        .catch(err => {
            return res.json({
                err: err
            });
        });
};

exports.deleteCart = (req, res, next) => {
    const { id } = req.currentUser;
    const { cart_id } = req.body;

    CartModel.delete(cart_id)
        .then(async () => {
            return res.json({
                isSuccedd: true
            });
        })
        .catch(err => {
            return res.json({
                err: err
            });
        });
};

exports.postCartDeleteProduct = (req, res, next) => {
    const prodId = req.body.productId;
    req.user
        .removeFromCart(prodId)
        .then(result => {
            res.redirect('/cart');
        })
        .catch(err => {
            const error = new Error(err);
            error.httpStatusCode = 500;
            return next(error);
        });
};

exports.postOrder = (req, res, next) => {
    const token = req.body.stripeToken;
    let totalSum = 0;

    req.user
        .populate('cart.items.productId')
        .execPopulate()
        .then(user => {
            user.cart.items.forEach(p => {
                totalSum += p.quantity * p.productId.price;
            });
            const products = user.cart.items.map(i => {
                return { quantity: i.quantity, product: { ...i.productId._doc } };
            });
            const order = new Order({
                user: {
                    email: req.user.email,
                    userId: req.user    // Mongoose will pick the id
                },
                products: products
            });
            return order.save();
        })
        .then(result => {
            const charge = stripe.charges.create({
                amount: totalSum * 100,
                currency: 'usd',
                description: 'Your Order',
                source: token,
                metadata: { order_id: result._id.toString() }
            });
            return req.user.clearCart();
        })
        .then(() => {
            res.redirect('/orders');
        })
        .catch(err => {
            const error = new Error(err);
            error.httpStatusCode = 500;
            return next(error);
        });
};

exports.getOrders = (req, res, next) => {
    Order.find({ "user.userId": req.user._id })
        .then(orders => {
            res.render('shop/orders', {
                path: '/orders',
                pageTitle: 'Your Orders',
                orders: orders
            });
        })
        .catch(err => {
            const error = new Error(err);
            error.httpStatusCode = 500;
            return next(error);
        });
};

exports.getInvoice = (req, res, next) => {
    const orderId = req.params.orderId;
    Order.findById(orderId)
        .then(order => {
            if (!order) {
                return next(new Error('No order found'));
            }
            if (order.user.userId.toString() !== req.user._id.toString()) {
                return next(new Error('Unauthorized'));
            }
            const invoiceName = 'invoice-' + orderId + '.pdf';
            const invoicePath = path.join('data', 'invoices', invoiceName);

            const pdfDoc = new PDFDocument();
            res.setHeader('Content-Type', 'application/pdf');
            res.setHeader('Content-Disposition', 'inline; filename="' + invoiceName + '"'); // replace inline with attachment for download
            // pipe into a writable stream
            pdfDoc.pipe(fs.createWriteStream(invoicePath));
            pdfDoc.pipe(res);

            pdfDoc.fontSize(26).text('Invoice', {
                underline: true
            });
            pdfDoc.fontSize(14).text('--------------------------');
            let totalPrice = 0;
            order.products.forEach(prod => {
                totalPrice += prod.quantity * prod.product.price;
                pdfDoc.fontSize(14).text(
                    prod.product.title +
                    ' - ' +
                    prod.quantity +
                    ' x ' +
                    '$' +
                    prod.product.price
                );
            });
            pdfDoc.text('--------------------------');
            pdfDoc.fontSize(20).text('Total Price: $' + totalPrice);

            pdfDoc.end();
        })
        .catch(err => next(err));
};