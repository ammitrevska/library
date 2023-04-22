import './App.css';
import React, {Component} from "react";
import Books from "../Books/BookList/books";
import LibraryService from "../../repository/libraryRepository";
import {BrowserRouter as Router, Routes, Navigate, Route} from "react-router-dom";
import Header from "../Header/header";
import Categories from "../Categories/categories";
import Authors from "../Authors/authors";
import BookAdd from "../Books/BooksAdd/BookAdd";
import BookEdit from "../Books/BookEdit/BookEdit";

//kje imame deca komponenti na app
//statefull component chuva state

class App extends Component{

    constructor(props) {
        super(props);
        this.state = {
            books: [],
            categories: [],
            authors: [],
            selectedBook: {}
        }
    }


    loadBooks = () => {
        LibraryService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }

    loadCategories = () => {
        LibraryService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            });
    }

    loadAuthors = () => {
        LibraryService.fetchAuthors()
            .then((data) => {
                this.setState({
                    authors: data.data
                })
            });
    }


    deleteBook = (id) => {
        LibraryService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            })
    }

    addBook = (name,category,author,availableCopies) => {
        LibraryService.addBook(name,category,author,availableCopies)
            .then(() => {
                this.loadBooks();
            });
    }

    getBook = (id) => {
        LibraryService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            });
    }

    editBook = (id,name,category,author,availableCopies) => {
        LibraryService.editBook(id,name,category,author,availableCopies)
            .then(() =>
            {
                this.loadBooks();
            });
    }

    markAsTakenBook = (id, availableCopies) => {
        LibraryService.markAsTakenBook(id, availableCopies)
            .then(() =>{
                this.loadBooks();
            })
    }


    componentDidMount() {
        this.loadBooks();
        this.loadCategories();
        this.loadAuthors();
    }

    //todo: Navigate??
    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div className="container">
                        <Routes>
                            {/*patekite se redat od pospecifichna kon pogeneralna*/}
                            <Route path="/categories" element={<Categories categories={this.state.categories} />} />
                            {/* eslint-disable-next-line react/jsx-no-comment-textnodes */}
                            <Route path="/author" element={<Authors authors={this.state.authors} />}/>
                            <Route path="/books/add" element={<BookAdd
                                categories={this.state.categories}
                                authors={this.state.authors}
                                onAddBook={this.addBook}/>
                                }/>
                            <Route path="/books/edit/:id" element={<BookEdit
                                categories={this.state.categories}
                                authors={this.state.authors}
                                onEditBook={this.editBook}
                                book={this.state.selectedBook}/>
                                }/>
                            <Route path="/books"
                                   element={<Books books={this.state.books}
                                                   onDelete={this.deleteBook}
                                                    onEdit={this.getBook}
                                                   onTaken={this.markAsTakenBook}
                                   />} />
                        </Routes>
                        {/*<Navigate to="/books" replace={true} />*/}
                    </div>
                </main>
            </Router>
        );
    }

}
export default App;
