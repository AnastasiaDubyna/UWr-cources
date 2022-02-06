const express = require('express');
const router = express.Router();
const controller = require("../controllers/homeController");

router.get("/", controller.renderHomePage);

router.post("/statistics", controller.postStatistics);

module.exports = router;