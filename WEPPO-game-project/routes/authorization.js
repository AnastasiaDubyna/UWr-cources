const express = require('express');
const router = express.Router();
const controller = require("../controllers/authController");

router.get("/", controller.authorization)

router.post("/login", controller.login);

router.post("/signup", controller.registration);

router.post("/anon", controller.anonymous);

module.exports = router;