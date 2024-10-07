const express = require('express');
const shopController = require('../controllers/shop.controller');
const auth = require('../middleware/auth.middleware');
const { createReviewSchema } = require('../middleware/validators/reviewValidator.middleware');

const router = express.Router();

router.get('/cart/:cartId', auth(), shopController.getCart);
router.post('/cart', auth(), shopController.postCart);
router.post('/cart-delete-item', auth(), shopController.postCartDeleteProduct);
router.post('/orders', auth(), shopController.getOrders);
router.post('/reviews', createReviewSchema, auth(), shopController.postReview);
router.get('/orders/:orderId', auth(), shopController.getOrders);

module.exports = router;
