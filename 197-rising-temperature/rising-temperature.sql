/* Write your PL/SQL query statement below */
Select w1.id
FROM weather w1, weather w2
WHERE w1.recordDate - w2.recordDate = 1
AND w1.temperature > w2.temperature;