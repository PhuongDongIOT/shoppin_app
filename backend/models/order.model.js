const query = require('../db/db-connection');
const { multipleColumnSet } = require('../utils/common.utils');

class OrderModel {
    tableOrder = 'orders';

    find = async (params = {}) => {
        let sql = `SELECT * FROM ${this.tableUser}`;
        if (!Object.keys(params).length) return await query(sql);
        const { columnSet, values } = multipleColumnSet(params)
        sql += ` WHERE ${columnSet}`;
        return await query(sql, [...values]);
    }

    findOne = async (params) => {
        const { columnSet, values } = multipleColumnSet(params)
        const sql = `SELECT * FROM ${this.tableUser}
        WHERE ${columnSet}`;
        const result = await query(sql, [...values]);
        return result[0];
    }

    create = async ({ user_id }) => {
        const sqlUser = `INSERT INTO ${this.tableUser}
        (user_id, is_deleted,status) VALUES (?, ?, ?)`;
        const resultuser = await query(sqlUser, [user_id, 0, 1]);
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
        const sql = `DELETE FROM ${this.tableUser}
        WHERE id = ?`;
        const result = await query(sql, [id]);
        const affectedRows = result ? result.affectedRows : 0;
        return affectedRows;
    }
}

module.exports = new OrderModel;
