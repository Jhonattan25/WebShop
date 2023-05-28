const mysqlConnection = require("../config/mysql");
const axios = require('axios');

let getAll = (req, res) => {
  mysqlConnection.query(`SELECT * FROM ${process.env.TABLE_PURCHASE}`, (err, rows) => {
    if (!err) {
      return res.status(200).json(rows);
    } else {
      console.log(err);
    }
  });
};

let hacerPostConToken= async (req) => {
  let products = req.body.products;

  try {
    let data = {
      "productStockList": products
    }
    
    const token = req.headers['authorization'];

    const response = await axios.put(`${process.env.GATEWAY_SERVICE}api/product/stock`, data, {
      headers: {
        'Authorization': token
      }
    });
    return response.status;
  } catch (error) {
    console.error(error);
  }
}

let getById = (req, res) => {
  const {id} = req.params;

  let select = `SELECT * FROM ${process.env.TABLE_PURCHASE} WHERE id = ?`;
  let query = mysqlConnection.format(select, [id]);
  mysqlConnection.query(query, (err, rows, fields) => {
    if (!err) {
      return res.status(200).json(rows);
    } else {
      console.log(err);
    }
  });
};

let create = async (req, res) => {
  let isValid = await hacerPostConToken(req);
  delete req.body.products;
  if (isValid == 200) {
    let insert = `INSERT INTO ${process.env.TABLE_PURCHASE} SET ?`;
    let query = mysqlConnection.format(insert, req.body);
    mysqlConnection.query(query, (error, result) => {
        if (error) throw error;
        res.json([{ respuesta: `Compra agregada con exito.` }]);
      }
    );
  }else{
    return res.status(400).json({"message":"no hay disponibilidad de productos"});
  }

};

let update = (req, res) => {

  const id = req.params.id;
  mysqlConnection.query(
    `UPDATE ${process.env.TABLE_PURCHASE} SET ? WHERE id = ?`,
    [req.body, id],
    (error, result) => {
      if (error) throw error;
      res.json([{ status: `Compra actualizada exitosamente.` }]);
    }
  );
};

module.exports = {
  getAll,
  getById,
  create,
  update
};
