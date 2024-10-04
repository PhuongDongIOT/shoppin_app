const express = require('express');
// body() will look for a specific field but it has to be in the request body
const authController = require('../controllers/auth');
const { createUserSchema, updateUserSchema, validateLogin } = require('../middleware/validators/userValidator.middleware');
// const auth = require('../middleware/auth.middleware');

const router = express.Router();

router.post(
    '/login',
    createUserSchema,
    authController.postLogin
);
router.post(
    '/signup',
    createUserSchema,
    authController.postSignup
);
router.post('/logout', authController.postLogout);
router.post('/reset', authController.postReset);
router.post('/new-password', authController.postNewPassword);

module.exports = router;