const express = require('express');
const shopController = require('../controllers/shop.controller');
const auth = require('../middleware/auth.middleware');

const router = express.Router();

router.get('/products/:productId', shopController.getProduct);
router.post('/cart', auth(), shopController.postCart);
router.post('/cart-delete-item', auth(), shopController.postCartDeleteProduct);
router.get('/orders', auth(), shopController.getOrders);
router.get('/orders/:orderId', auth(), shopController.getInvoice);

module.exports = router;
