import logging
import sys
import getopt
from sqlalchemy.engine import create_engine
from sqlalchemy.log import echo_property
from sqlalchemy.orm import declarative_base, relationship, session
from sqlalchemy.orm.relationships import foreign
from sqlalchemy.sql.elements import ColumnElement
from sqlalchemy.sql.expression import false, null, select, table
from sqlalchemy.sql.operators import op
from sqlalchemy.sql.schema import ForeignKey, MetaData, Table, Column
from sqlalchemy.sql.sqltypes import Integer, String
from sqlalchemy.orm import sessionmaker
from sqlalchemy.sql import exists
import json


#SQLAlchemy configurations
Base = declarative_base()

engine = create_engine("postgresql://root:root@localhost:5432/test", echo=True)

metadata_o = MetaData(engine)

Session = sessionmaker(bind=engine)


#creating tables in data base
friend = Table(
    "borrower", 
    metadata_o, 
    Column('id', Integer, primary_key=True, nullable=False),
    Column('firstname', String, nullable=True), 
    Column('lastname', String, nullable=True),
    Column('email', String, nullable=False, unique=True)
)

book = Table(
    "book",
    metadata_o, 
    Column('id', Integer, primary_key=True, nullable=False),
    Column('author', String, nullable=False),
    Column('title', String, nullable=False),
    Column('borrower_id', Integer, ForeignKey('borrower.id'), nullable=True)
)

metadata_o.create_all(engine)


#declaring mapping between classes and tables. Also declaring relaionships between them
class Borrower(Base):
    __tablename__ = "borrower"
    id = Column(Integer, primary_key=True)
    firstname = Column(String)
    lastname = Column(String)
    email = Column(String, unique=True)
    borrowed_books = relationship("Book")  

class Book(Base):
    __tablename__ = "book"
    id = Column(Integer, primary_key=True)
    author = Column(String)
    title = Column(String)
    borrower_id = Column(Integer, ForeignKey(Borrower.id))
    borrower = relationship("Borrower", back_populates="borrowed_books")


def add_book(author, title):
    with Session() as session:
        session.add(Book(author = author, title = title))
        session.commit()

def find_or_add_new_borrower(email, firstname=None, lastname=None):
    with Session() as session:
        borrower = session.query(Borrower).filter_by(email=email).first()
    
        if borrower is None:
            borrower = Borrower(firstname = firstname, lastname = lastname, email = email)
            session.add(borrower)
            session.commit()
    
        return borrower

def borrow_book(author, title, borrower_email):
    with Session() as session:
        borrower = find_or_add_new_borrower(email = borrower_email)
        
        book_to_borrow = session.query(Book).filter(
                Book.author == author, Book.title == title, Book.borrower == None
            ).first()

        if book_to_borrow:
            book_to_borrow.borrower = borrower
        else:
            logging.error("Book is already borrowed or not exists.")
        session.commit()


def return_book(author, titile, borrower_email):
    with Session() as session:
        borrowed_book = session.query(Book).filter(
            Book.author == author, Book.title == titile, Book.borrower.has(email=borrower_email)
        ).first()
        if borrowed_book:
            borrowed_book.borrower = None
        else:
            logging.error("This book was not borrowed by this person.")
        session.commit()
    

argumentList = sys.argv[1:]
options = "a:b:r:"
long_options = ["add_book =", "borrow_book =", "return_book ="]

opts, args = getopt.getopt(argumentList, options, long_options)
# input example  python main.py -a '{\"author\":\"Paolini\",\"title\":\"Eragon\"}'
for option, arg in opts:
    input = json.loads(arg)
    if option in ["-a", "--add_book"]:
        add_book(author=input["author"], title=input["title"])
    if option in ["-b", "--borrow_book"]:
        borrow_book(author=input["author"], title=input["title"], borrower_email=input["email"])
    if option in ["-r", "--return_book"]:
        return_book(author=input["author"], titile=input["title"], borrower_email=input["email"])






    








