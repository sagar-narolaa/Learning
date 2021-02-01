insert= INSERT INTO books (Book_Name,ISBN,Author) VALUES (?, ?, ?);

update_by_id=update books set Book_Name = ?,ISBN= ?, Author =? where ID =?;

delete_by_id=delete from books where ID = ?;

see_all_records=select * from books;

see_by_id=select ID,Book_Name, ISBN, Author from books where ID =?

convert_to_excel=select ID,Book_Name,ISBN,Author from books;

insert_user= INSERT INTO users (FNAME,LNAME,EMAIL,PASSWORD) VALUES (?, ?, ?, ?);

verify_user=select EMAIL,PASSWORD from users where EMAIL=? AND PASSWORD=?;

user_exist=select EMAIL from users where FNAME=? AND LNAME=?;