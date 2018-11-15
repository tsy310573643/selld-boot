<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>错误页</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-danger alert-dismissable">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    错误!
                </h4> <strong>${msg}</strong><a href="#" class="${url}">3秒后跳转</a>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    setTimeout( function () {
        location.href ="${url}";
    }, 3000)
</script>
</html>