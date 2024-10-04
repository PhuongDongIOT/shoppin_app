const query = require('../db/db-connection');
const { multipleColumnSet } = require('../utils/common.utils');

    class ReviewModel {
    tableReviews = 'reviews';

    find = async (params = {}) => {
        let sql = `SELECT * FROM ${this.tableReviews}`;
        if (!Object.keys(params).length) return await query(sql);
        const { columnSet, values } = multipleColumnSet(params)
        sql += ` WHERE ${columnSet}`;
        return await query(sql, [...values]);
    }

    findOne = async (params) => {
        const { columnSet, values } = multipleColumnSet(params)
        const sql = `SELECT * FROM ${this.tableReviews}
        WHERE ${columnSet}`;
        const result = await query(sql, [...values]);
        return result[0];
    }

    create = async ({ user_id, category_id, product_id, rating, comment }) => {
        const sqlUser = `INSERT INTO ${this.tableReviews}
        (user_id, category_id, rating, product_id, comment) VALUES (?, ?, ?, ?, ?)`;
        const resultuser = await query(sqlUser, [user_id, category_id, product_id, rating, comment]);
        const affectedRows = resultuser ? resultuser.affectedRows : 0;
        return affectedRows;
    }

    update = async (params, id) => {
        const { columnSet, values } = multipleColumnSet(params)
        const sql = `UPDATE user SET ${columnSet} WHERE id = ?`;
        const result = await query(sql, [...values, id]);
        return result;
    }

    delete = async (id) => {
        const sql = `DELETE FROM ${this.tableReviews}
        WHERE id = ?`;
        const result = await query(sql, [id]);
        const affectedRows = result ? result.affectedRows : 0;
        return affectedRows;
    }
}

module.exports = new ReviewModel;
