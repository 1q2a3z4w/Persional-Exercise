let mysql = require('mysql')
let connection = mysql.createConnection({
	host:"localhost",
	user:"root",
	password:"",
	port:"3306",
	database:"hechang"
})
connection.connect()
let sql = 'select * from user;'
connection.query(sql,function(err,res){
	if(err){
		console.log("连接失败")
		}
		console.log(res)
})
