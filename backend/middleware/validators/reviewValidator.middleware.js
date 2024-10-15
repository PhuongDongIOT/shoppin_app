const { body } = require('express-validator');

exports.createReviewSchema = [
    body('comment', 'Please enter a valid comment')
        .isLength({ min: 1 })
        .trim()
];

exports.createOrderSchema = [
    body('cart_id', 'Please enter a valid comment')
        .isLength({ min: 1 })
        .trim()
];
