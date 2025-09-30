package com.example.project.models.name

data class Name(
    val id: Int,
    val name: String,
)
data class NameResponse(
    val data: List<Name>,
    val status: String,
    val count: Int,
)

const val nameJson = """
{
    "status": "success",
    "count": 50,
    "data": [
    { "id": 1, "name": "Alice" },
    { "id": 2, "name": "Bob" },
    { "id": 3, "name": "Charlie" },
    { "id": 4, "name": "David" },
    { "id": 5, "name": "Akshay" },
    { "id": 6, "name": "Frank" },
    { "id": 7, "name": "Akshay" },
    { "id": 8, "name": "Hannah" },
    { "id": 9, "name": "Ian" },
    { "id": 10, "name": "Jack" },
    { "id": 11, "name": "Karen" },
    { "id": 12, "name": "Leo" },
    { "id": 13, "name": "Mia" },
    { "id": 14, "name": "Nathan" },
    { "id": 15, "name": "Olivia" },
    { "id": 16, "name": "Paul" },
    { "id": 17, "name": "Quinn" },
    { "id": 18, "name": "Rachel" },
    { "id": 19, "name": "Sam" },
    { "id": 20, "name": "Tina" },
    { "id": 21, "name": "Uma" },
    { "id": 22, "name": "Victor" },
    { "id": 23, "name": "Wendy" },
    { "id": 24, "name": "Xander" },
    { "id": 25, "name": "Yara" },
    { "id": 26, "name": "Zane" },
    { "id": 27, "name": "Amy" },
    { "id": 28, "name": "Brian" },
    { "id": 29, "name": "Cathy" },
    { "id": 30, "name": "Derek" },
    { "id": 31, "name": "Ella" },
    { "id": 32, "name": "Fred" },
    { "id": 33, "name": "Gina" },
    { "id": 34, "name": "Harry" },
    { "id": 35, "name": "Isla" },
    { "id": 36, "name": "Jake" },
    { "id": 37, "name": "Kylie" },
    { "id": 38, "name": "Liam" },
    { "id": 39, "name": "Molly" },
    { "id": 40, "name": "Noah" },
    { "id": 41, "name": "Olga" },
    { "id": 42, "name": "Peter" },
    { "id": 43, "name": "Queen" },
    { "id": 44, "name": "Ryan" },
    { "id": 45, "name": "Sophia" },
    { "id": 46, "name": "Tom" },
    { "id": 47, "name": "Ursula" },
    { "id": 48, "name": "Violet" },
    { "id": 49, "name": "William" },
    { "id": 50, "name": "Zoe" }
    ]
}
"""