const express = require('express');
const { body } = require('express-validator');
// body() will look for a specific field but it has to be in the request body
const authController = require('../controllers/auth.controller');
const { createUserSchema, loginUserSchema, resetUserSchema } = require('../middleware/validators/userValidator.middleware');
const auth = require('../middleware/auth.middleware');

const router = express.Router();

router.post(
    '/login',
    loginUserSchema,
    authController.postLogin
);
router.post(
    '/signup',
    createUserSchema,
    auth(),
    authController.postSignup
);
router.post('/logout', authController.postLogout);
router.post('/reset', resetUserSchema, authController.postReset);
router.post('/new-password', resetUserSchema, authController.postNewPassword);

module.exports = router;