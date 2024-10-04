const query = require('../db/db-connection');
const { multipleColumnSet } = require('../utils/common.utils');

class CredentialModel {
    tableCredential = 'credentials';

    find = async (params = {}) => {
        let sql = `SELECT * FROM ${this.tableCredential}`;
        if (!Object.keys(params).length) return await query(sql);
        const { columnSet, values } = multipleColumnSet(params)
        sql += ` WHERE ${columnSet}`;
        return await query(sql, [...values]);
    }

    findOne = async (params) => {
        const { columnSet, values } = multipleColumnSet(params)
        const sql = `SELECT * FROM ${this.tableCredential}
        WHERE ${columnSet}`;
        const result = await query(sql, [...values]);
        return result[0];
    }

    create = async ({ provider_id, provider_id, user_id, hasher, password_hash, password_salt }) => {
        const sqlCredential = `INSERT INTO ${this.tableCredential}
        (provider_id, provider_id, user_id, hasher, password_hash, password_salt) VALUES (?, ?, ?, ?, ?, ?)`;
        const resultuser = await query(sqlCredential, [provider_id, provider_id, user_id, hasher, password_hash, password_salt]);
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
        const sql = `DELETE FROM ${this.tableCredential}
        WHERE id = ?`;
        const result = await query(sql, [id]);
        const affectedRows = result ? result.affectedRows : 0;
        return affectedRows;
    }
}

module.exports = new CredentialModel;
