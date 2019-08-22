<!doctype html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Emp</title>
</head>
<body>
<h2>Emp manager!</h2>
<table>
  <thead>
  <tr>
    <th>Empno</th>
    <th>Ename</th>
    <th>Opr</th>
  </tr>
  </thead>
<#-- 判断empList是否为空 -->
<#if empList??>
  <tbody>
  <#-- 循环输出emp信息 -->
<#list empList as emp>
<tr>
  <td>${emp.empno}</td>
  <td>${emp.ename}</td>
  <td>
    <a href="javascript:void(0)" class="emp-del">Del</a>
    <a href="javascript:void(0)" class="emp-edit">Edit</a>
  </td>
</tr>
</#list>
  </tbody>
</#if>
</table>
</body>
</html>
