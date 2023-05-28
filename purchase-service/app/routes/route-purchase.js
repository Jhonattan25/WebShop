const express = require("express");
const purchaseController = require('../controllers/purchase-controller');
const router = express.Router();

// GET all purchase
router.get('/', purchaseController.getAll);

// GET purchase By id
router.get("/:id", purchaseController.getById);

// POST  add purchase
router.post("/add", purchaseController.create);

// PUT  update purchase
router.put("/update/:id", purchaseController.update);

module.exports = router;