var http = require('http'); //引入http
var querystring = require('querystring');
var url = require('url'); //引入url
var fs = require('fs'); //引入文件管理

http.createServer(function(req, res) {
	if (req.url == '../../../picture/图标.ico') {
		return; //加载图标也会被当做一次http请求
	};
	var pathname = url.parse(req.url).pathname; //解析地址栏地址
	var body = '';
	//使用fs文件管理读取相对应文件
	fs.readFile(pathname.substring(1) + '.html', function(err, data) {
		if (err) { //如果错误存在说明文件不存在
			res.writeHead(404, {
				'Content-Type': 'text/html; charset=utf-8'
			});
			res.write('404页面不存在');
		} else { //否则文件读取成功
			res.writeHead(200, {
				'Content-Type': 'text/html; charset=urf-8'
			});
			res.write(data); //写入文件
		};
		res.end();
	})
}).listen(3000);