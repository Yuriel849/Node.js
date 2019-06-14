const express = require('express');
const { isLoggedIn, isNotLoggedIn } = require('./middlewares');
const { Post, User } = require('../models');
const router = express.Router();

router.get('/profile', isLoggedIn, (req, res) => { // isLoggedIn middleware is used before preceeding
    res.render('profile', { title: 'My Page', user: req.user });
});

router.get('/join', isNotLoggedIn, (req, res) => { // isNotLoggedIn middleware is used before preceeding
    res.render('join', {
        title: 'Join',
        user: req.user,
        joinError: req.flash('joinError'),
    });
});

router.get('/', (req, res, next) => {
    Post.findAll({
        include: {
            model: User,
            attributes: ['id', 'nick'],
        },
        order: [['createdAt', 'DESC']],
    })
        .then((posts) => {
            res.render('main', {
                title: 'SNS Service',
                twits: posts,
                user: req.user,
                loginError: req.flash('loginError'),
            });
        })
        .catch((error) => {
            console.error(error);
            next(error);
        });
});

module.exports = router;