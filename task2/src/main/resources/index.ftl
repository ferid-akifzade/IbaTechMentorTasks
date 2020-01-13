<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form method="post">
    First number
    <label>
        <input name="first">
    </label><br/><br/>
    <label>
        <select name="operation">
            <option value="+">+</option>
            <option value="-">-</option>
            <option value="*">*</option>
            <option value="/">/</option>
            <option value="%">%</option>
        </select>
    </label>
    <br/><br/>
    Second number
    <label>
        <input name="second">
    </label> <br/><br/>
    <input type="submit" name="submit" value="Calculate"><br/><br/>
    <p> ${result}</p>
    <a href="/history"> Go to history</a>
</form>
</body>
</html>