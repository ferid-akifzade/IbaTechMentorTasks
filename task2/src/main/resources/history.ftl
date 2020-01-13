<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>History</title>
</head>
<body>
<table>
    <tr>
        <th>number1</th>
        <th>operation</th>
        <th>number2 </th>
        <th>result</th>
    </tr>

<#list results as result>
    <tr>
        <td>
            ${result.firstNumber}
        </td>
        <td>
            ${result.secondNumber}
        </td>
        <td>
            ${result.operation}
        </td>
        <td>
            ${result.result}
        </td>
    </tr>
</#list>
</table>
</body>
</html>