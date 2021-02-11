insert_book= INSERT INTO books (Book_Name,ISBN,Author) VALUES (?, ?, ?);

update_book=update books set Book_Name = ?,ISBN= ?, Author =? where ID =?;

delete_book_by_id=delete from books where ID = ?;

get_all_books=select * from books;

select_book_by_id=select ID,Book_Name, ISBN, Author from books where ID =?



convert_to_excel=select ID,Book_Name,ISBN,Author from books;

insert_user= INSERT INTO users (FNAME,LNAME,EMAIL,PASSWORD) VALUES (?, ?, ?, ?);

verify_user=select EMAIL,PASSWORD from users where EMAIL=? AND PASSWORD=?;

user_exist=select EMAIL from users where FNAME=? AND LNAME=?;