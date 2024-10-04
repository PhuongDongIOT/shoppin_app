const express = require('express');
const adminController = require('../controllers/admin');
const auth = require('../middleware/auth.middleware');
const { createUserSchema, updateUserSchema, validateLogin } = require('../middleware/validators/userValidator.middleware');

const router = express.Router();

router.post(
    '/add-product',
    createUserSchema,
    // auth(),
    adminController.postAddProduct
);
router.get('product/:productId', auth(), adminController.getEditProduct);
router.post(
    '/edit-product',
    createUserSchema,
    auth(),
    adminController.postEditProduct
);
router.delete('/product/:productId', auth(), adminController.deleteProduct);

module.exports = router;