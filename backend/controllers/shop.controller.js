const { validationResult } = require('express-validator/check');
const ProductModel = require('../models/product.model');
const CartModel = require('../models/cart.model');
const CartItemModel = require('../models/cart-item.model');
const OrderModel = require('../models/order.model');
const OrderLineModel = require('../models/order-line.model');
const ReviewModel = require('../models/review.model');
const { checkEmptyArray } = require('../utils/array.utils');

exports.getCart = (req, res, next) => {
    const { cartId } = req.param;
    CartItemModel.find({ cartId })
        .then(async (listProduct) => {
            return res.json({
                cart_id: cartId,
                products: listProduct
            });
        })
        .catch(err => {
            return res.json({
                err: err
            });
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
                        quantity: products.quantity,
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
    // const { id } = req.currentUser;
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
    const { cart_id, product_id } = req.body;
    CartItemModel.delete({
        cart_id,
        product_id
    }).then(result => {
        return res.json({
            isSuccedd: true
        });
    }).catch((error) => {
        return res.json({
            err: error
        });
    })
};

exports.postOrder = (req, res, next) => {
    const { id } = req.currentUser;
    const { cart_id } = req.body;

    CartItemModel.find({ cart_id })
        .then(async (listProduct) => {
            if (checkEmptyArray(listProduct)) {
                await OrderModel.create({ id })
                for (item of products) {
                    const { product_id } = item;
                    await OrderLineModel.create({
                        cart_id: idCart,
                        product_id,
                        quantity: listProduct.quantity,
                        price: listProduct.price
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

exports.getOrders = (req, res, next) => {
    const { orderId } = req.param;
    OrderLineModel.find({ orderId })
        .then(async (listProduct) => {
            return res.json({
                order_id: orderId,
                products: listProduct
            });
        })
        .catch(err => {
            return res.json({
                err: err
            });
        });
};

exports.postReview = (req, res, next) => {
    const { id } = req.currentUser;
    const { category_id, product_id, rating, comment } = req.body;
    const errors = validationResult(req);
    if (!errors.isEmpty()) return res.json({
        err: errors
    });

    ReviewModel.create({ user_id: id, category_id, product_id, rating, comment })
        .then(async (idCart) => {
            if (checkEmptyArray(products)) {
                for (item of products) {
                    const { product_id } = item;
                    const product = await ProductModel.findOne({ id: product_id })
                    await CartItemModel.create({
                        cart_id: idCart,
                        product_id,
                        quantity: products.quantity,
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
