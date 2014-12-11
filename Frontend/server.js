var sys = require("sys");
var fs = require("fs");
var https = require("https");
var static = require('node-static');

var file = new static.Server('./app');


var options = {
  key: fs.readFileSync('ssl/frontend.key'),
  cert: fs.readFileSync('ssl/frontend.crt'),
  requestCert: false,
  rejectUnauthorized: false
};

https.createServer(options, function (req, res) {
 
 	req.addListener('end', function () {
        file.serve(req, res);
    }).resume();


}).listen(8000);

sys.puts("Frontend Server Started on https://localhost:8000");