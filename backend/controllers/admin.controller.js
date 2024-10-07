const fileHelper = require('../util/file');
const { validationResult } = require('express-validator/check');
const Product = require('../models/product.model');
const ProductModel = require('../models/product.model');
const CategoryModel = require('../models/category.model');

exports.postAddCategory = (req, res, next) => {
    const { parent_category, slug, name, description } = req.body;
    const errors = validationResult(req);
    if (!errors.isEmpty()) return res.json({
        err: errors
    });
    CategoryModel.create({
        parent_category, slug, name, description
    }).then(async (idCategory) => {
        return res.json({
            id: idCategory
        });
    }).catch(error => {
        return res.json({
            err: error
        });
    })
};

exports.postDeleteCategory = (req, res, next) => {
    const { id } = req.body;
    const errors = validationResult(req);
    if (!errors.isEmpty()) return res.json({
        err: errors
    });
    CategoryModel.delete({
        id
    }).then(async (idCategory) => {
        return res.json({
            isSuccedd: true
        });
    }).catch(error => {
        return res.json({
            err: error
        });
    })
};

exports.postAddProduct = (req, res, next) => {
    const { category_id, title, slug, summary, description, price, created_by, image } = req.body;

    const errors = validationResult(req);
    if (!errors.isEmpty()) return res.json({
        err: errors
    });
    const imageUrl = image?.path ?? null;
    ProductModel.create({
        category_id,
        title,
        slug,
        picture: imageUrl,
        summary,
        description,
        price,
        created_by
    }).then(result => {
        return res.json({
            id: result
        });
    }).catch(errors => {
        return res.json({
            err: errors
        });
    });
};

exports.postEditProduct = (req, res, next) => {
    const { id, category_id, title, slug, summary, description, price, created_by, image } = req.body;

    const errors = validationResult(req);
    if (!errors.isEmpty() || id) return res.json({
        err: errors
    });
    const imageUrl = image?.path ?? null;
    ProductModel.update({
        category_id,
        title,
        slug,
        picture: imageUrl,
        summary,
        description,
        price,
        created_by
    }, id).then(result => {
        return res.json({
            id: result
        });
    }).catch(errors => {
        return res.json({
            err: errors
        });
    });
};

exports.deleteProduct = (req, res, next) => {
    const { productId } = req.params;
    ProductModel.delete(productId).then(result => {
        return res.json({
            isSuccedd: true
        });
    }).catch(errors => {
        return res.json({
            err: errors
        });
    });
};