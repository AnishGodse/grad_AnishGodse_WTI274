<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Details</title>
<style>
    body { font-family: Arial, sans-serif; margin: 24px; }
    h1 { color: #333; }
    form { max-width: 420px; padding: 16px; border: 1px solid #ddd; border-radius: 8px; }
    label { display: block; margin-top: 12px; font-weight: bold; }
    input[type="text"], input[type="number"] { width: 100%; padding: 8px; margin-top: 6px; box-sizing: border-box; }
    .btns { margin-top: 16px; display: flex; gap: 8px; }
    .result { margin-top: 24px; padding: 16px; background: #f9f9f9; border: 1px dashed #bbb; border-radius: 8px; }
    .row { margin: 6px 0; }
    .label { font-weight: bold; }
</style>
</head>
<body>
    <h1>======= Student Details Page =======</h1>

    
    <form method="post" action="addStudent">
        <label>Roll No</label>
        <input type="number" name="rollNo" required min="1"/>

        <label>Name</label>
        <input type="text" name="name" required/>

        <label>Standard</label>
        <input type="number" name="standard" required min="1"/>

        <label>School</label>
        <input type="text" name="school" required/>

        <button type="submit">INSERT</button>
        <button type="reset">RESET</button>

    </form>

</body>
</html>