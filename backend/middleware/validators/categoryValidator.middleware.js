const { body } = require('express-validator');

exports.createCategorySchema = [
    body('name', 'Please enter a valid name')
        .isLength({ min: 1 })
        .isAlphanumeric()
        .trim(),
    body('image', 'Please enter a valid image')
        .isLength({ min: 1 })
        .isAlphanumeric()
        .trim()
];
