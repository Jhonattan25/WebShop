require('dotenv').config();
const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');
const routesPurchase = require('./routes/route-purchase'); 

const app = express()
  .use(cors({ credentials: true, origin: "http://localhost:4200" }))
  .use(bodyParser.urlencoded({ extended: false }))
  .use(bodyParser.json());
  
app.use('/api/purchase', routesPurchase);
module.exports = app;