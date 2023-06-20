const express = require('express') //创建服务器
const bodyParser = require("body-parser")
var mysql = require('mysql2'); //引入mysql模块
var cors = require("cors");


const app = express() //实例化服务器

//配置解析post 请求参数
app.use(bodyParser.urlencoded({
	extended: false
}))
app.use(bodyParser.json())
app.use(cors())
//连接数据库
let con_db = {
	host: 'localhost', //地址
	port: '3306',
	user: 'root', //用户名
	password: '123456', //密码 
	database: 'hechang' //要链接的数据库名字
}
let db = mysql.createConnection(con_db);
db.connect(function(err) {
	if (err) {
		console.log(`mysql连接失败: ${err}!`);
	} else {
		console.log("mysql连接成功!");
	}
});

app.listen(8000, () => {
	console.log("服务器创建成功")
}) //设置端口

let data = []
//添加消息
app.post('/register', (req, res) => {
	console.log(req.body)
	let sql = "insert into user set user=?,pwd=?,sex=?,email=?;"
	db.query(sql, [req.body.user, req.body.pwd, req.body.sex, req.body.email], (error, result) => {
		console.log(result)
		if (result.affectedRows == 1) {
			res.send({
				message: "注册成功"
			})
		} else {
			res.send({
				message: "注册失败"
			})
		}
	})
	data.push(req.body)
})

app.get("/login", (req, res) => {
	var obj = req.query
	console.log(obj.user)
	let sql = "select pwd from user where user=?;";
	db.query(sql, [obj.user], (error, result) => {
		var resultJson = result;
		console.log(resultJson.length);
		if (resultJson.length === 0) {
			result = {
				code: 300,
				msg: '该账号不存在'
			};
			res.json(result);
		} else {
			db.query(sql, obj.user, (error, result) => {
				var temp = result[0].pwd; //取得数据库查询字段值
				console.log(temp);
				if (temp == obj.pwd) {
					result = {
						code: 200,
						msg: '密码正确'
					};
				} else {
					result = {
						code: 400,
						msg: '密码错误'
					};
				}
				res.json(result); // 以json形式，把操作结果返回给前台页面
				console.log('找到了')
			})
		}
	})
})
/* 
 router.post('/login', function(req, res, next) {
     // 获取请求字段
     console.log('post')
     var username = req.body.name;//获取前台请求的参数
     var password = req.body.pass;
     //console.log(password)
  
     // 启用连接池查询
     pool.getConnection(function(err, connection) {
         //先判断该账号是否存在
         
         var $sql = "select * from cus where USERNAME=?";
         connection.query($sql, [username], function (err, result) {
           var resultJson = result;
           console.log(resultJson.length);
           if (resultJson.length === 0) {
             result = {
               code: 300,
               msg: '该账号不存在'
             };
             res.json(result);
             connection.release();
           } else {  //账号存在，可以登录，进行密码判断
            
             var $sql1 = "select PASSWORD from cus where USERNAME=?";
             connection.query($sql1, [username], function (err, result) {  
               var temp = result[0].PASSWORD;  //取得数据库查询字段值
               console.log(temp);
               if (temp == password) {
                 result = {
                   code: 200,
                   msg: '密码正确'
                 };
               } else {
                 result = {
                   code: 400,
                   msg: '密码错误'
                 };
               }
               res.json(result); // 以json形式，把操作结果返回给前台页面
               connection.release();// 释放连接
               console.log('找到了')
             });
           }
         });
       });
 });*/
// const cors = require('cors')
// const { error } = require('console');
// var Event = require('events').EventEmitter;    //引入事件模块
// var query = new Event();//创建事件对象
// require('events').EventEmitter.defaultMaxListeners = 0;
/* connection.connect(function(err) { //链接数据库
	if (err) { //链接错误执行
		console.log('[错误]' + err);
		connection.end();
		return;
	};
	console.log('链接成功'); //否则链接成功
});
//托管资源
app.use(express.static('public'))
//请求跨域
app.use(cors())
//Express HTTP请求中 req请求消息 res响应消息
app.use(express.urlencoded({
	extended: true
}))
app.use(express.json())

app.get('/login', (req, res) => {
	//获取post传递的参数
	var obj = req.query
	var user=obj.user
	var pwd=obj.pwd
	//绑定login事件  传入 username password  链接数据库对象
	query.on('/login', function(user, pwd, connection) {
	//编写sql查询语句;
		var find = 'SELECT * FROM user WHERE uname = ' + user;
	//执行sql语句
		connection.query(find, function(err, result) {
			if (err) {   //链接失败 直接return;
				console.log('[错误]' + err);
				return;
			};
	
			if (result.length) {   //如果查到了数据
				console.log('------------start----------------');
				var string = JSON.stringify(result);
				var json = JSON.parse(string)[0];
				console.log(string)
				if (json.upwd == pwd) {
					console.log('密码校验正确');
					res.send("密码校验正确")
				} else {
	            console.log('密码校验错误');
				res.send("密码校验错误")
				}
				console.log('--------------end-----------------');
			} else {
				console.log('账号不存在')
				res.send("账号不存在")
			}
		})
	})
})
 */