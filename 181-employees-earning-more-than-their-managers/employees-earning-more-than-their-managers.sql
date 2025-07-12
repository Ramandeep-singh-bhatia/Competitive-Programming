/* Write your PL/SQL query statement below */
SELECT emp.name AS "Employee"
FROM Employee emp, Employee mgr
WHERE emp.managerId = mgr.id AND emp.salary > mgr.salary;