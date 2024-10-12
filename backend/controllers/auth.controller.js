const bcrypt = require('bcryptjs');
// const nodemailer = require('nodemailer');
// const sendgridTransport = require('nodemailer-sendgrid-transport');
const jwt = require('jsonwebtoken');
const dotenv = require('dotenv');
dotenv.config();
const { validationResult } = require('express-validator/check');
const UserModel = require('../models/user.model');
const credentialModel = require('../models/credential.model');

// const transporter = nodemailer.createTransport(sendgridTransport({
//     auth: {
//         api_key: 'SG.asT5R0LJSsOsGhR0VseMSw.lu2lWTKbuAllkd15qgjBs1j0SfldA16B6VFkFVXBwn0'
//     }
// }));

const passwwordDefault = '123456789';
let userSail = {
    user_id: null,
    hasher: '10',
    password_hash: '',
    password_salt: '10'
}

exports.postLogin = (req, res, next) => {
    const { email, password } = req.body;

    const errors = validationResult(req);
    if (!errors.isEmpty()) return res.json({
        err: errors
    });

    UserModel.findOne({ email: email })
        .then(async (user) => {
            if (!user || !password) return res.json({
                err: null
            });
            const passwordResult = await credentialModel.findOne({ user_id: user.id })

            bcrypt.compare(password, passwordResult.password_hash)
                .then(doMatch => {
                    const secretKey = process.env.SECRET_JWT || "";
                    user.id = `${user.id}`
                    const token = doMatch ? jwt.sign({ ...user }, secretKey, { expiresIn: 60 * 60 }) : '';
                    res.json({
                        data: {
                            token: token
                        }
                    })
                })
                .catch(err => {
                    return res.json({
                        err: err
                    });
                });
        })
        .catch(err => {
            return res.json({
                err: err
            });
        });
};

exports.postSignup = (req, res, next) => {
    const { slug, email, name, avatar, bio, company, password } = req.body;
    const errors = validationResult(req);
    if (!errors.isEmpty()) return res.json({
        err: errors
    });

    bcrypt.hash(password, 12)
        .then(async (hashedPassword) => {
            const idUser = await UserModel.create({
                slug, email, name, avatar, bio, company
            })
            userSail.password_hash = hashedPassword
            userSail.user_id = idUser
            if (idUser) await credentialModel.create(userSail)
            return res.json({
                id: idUser
            });
        })
        .catch(err => {
            return res.json({
                err: err
            });
        });
};

exports.postLogout = (req, res, next) => {
    return res.json({
        isSuccess: true
    });
};

exports.postReset = (req, res, next) => {
    const { email } = req.body;
    const errors = validationResult(req);
    if (!errors.isEmpty()) return res.json({
        err: errors
    });

    UserModel.findOne({ email: email })
        .then(async (user) => {
            if (!user) return res.json({
                err: null
            });
            bcrypt.hash(passwwordDefault, 12)
                .then(async (hashedPassword) => {
                    userSail.password_hash = hashedPassword
                    userSail.user_id = user.id
                    await credentialModel.delete(user.id)
                    await credentialModel.create(userSail)
                    return res.json({
                        id: user.id
                    });
                })
                .catch(err => {
                    return res.json({
                        err: err
                    });
                });
        })
        .catch(err => {
            return res.json({
                err: err
            });
        });
};

exports.postNewPassword = (req, res, next) => {
    const { email } = req.body;
    const errors = validationResult(req);
    if (!errors.isEmpty()) return res.json({
        err: errors
    });

    UserModel.findOne({ email: email })
        .then(async (user) => {
            if (!user) return res.json({
                err: null
            });
            bcrypt.hash(passwwordDefault, 12)
                .then(async (hashedPassword) => {
                    userSail.password_hash = hashedPassword
                    userSail.user_id = user.id
                    await credentialModel.delete(user.id)
                    await credentialModel.create(userSail)
                    return res.json({
                        id: user.id
                    });
                })
                .catch(err => {
                    return res.json({
                        err: err
                    });
                });
        })
        .catch(err => {
            return res.json({
                err: err
            });
        });
};
