// const { v4: uuidv4 } = require('uuid');
const query = require('../db/db-connection');
const { multipleColumnSet } = require('../utils/common.utils');

class OrderLineModel {
    tableCartItem = 'cart_items';

    find = async (params = {}) => {
        let sql = `SELECT * FROM ${this.tableCartItem}`;
        if (!Object.keys(params).length) return await query(sql);
        const { columnSet, values } = multipleColumnSet(params)
        sql += ` WHERE ${columnSet}`;
        return await query(sql, [...values]);
    }

    findOne = async (params) => {
        const { columnSet, values } = multipleColumnSet(params)
        const sql = `SELECT * FROM ${this.tableCartItem}
        WHERE ${columnSet}`;
        const result = await query(sql, [...values]);
        return result[0];
    }

    create = async ({ cart_id, product_id, price, quantity }) => {
        const sqlUser = `INSERT INTO ${this.tableCartItem}
        (cart_id, product_id, price, quantity) VALUES (?, ?, ?, ?)`;
        const resultuser = await query(sqlUser, [cart_id, product_id, price, quantity]);
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
        const sql = `DELETE FROM ${this.tableCartItem}
        WHERE id = ?`;
        const result = await query(sql, [id]);
        const affectedRows = result ? result.affectedRows : 0;
        return affectedRows;
    }
}

module.exports = new OrderLineModel;
