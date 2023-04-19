import './App.css';
import React, {Component} from "react";
import Books from "../Books/BookList/books";
import LibraryService from "../../repository/libraryRepository";
import {BrowserRouter as Router, Routes, Navigate, Route} from "react-router-dom";
import Header from "../Header/header";
import Categories from "../Categories/categories";

//kje imame deca komponenti na app
//statefull component chuva state

class App extends Component{

    constructor(props) {
        super(props);
        this.state = {
            books: [],
            categories: []
        }
    }

    //doesnt work
    // render() {
    //     return(
    //        <Router>
    //
    //            <main>
    //                <div className={"container"}>
    //                    <Routes>
    //                        <Route path={"/books"} exact render ={() =>
    //                            <Books books = {this.state.books}/>}/>
    //                        </Route>
    //                    </Routes>
    //                    {/*<Books books = {this.state.books}/>*/}
    //                </div>
    //            </main>
    //         </Router>
    //     );
    // }




    loadBooks = () => {
        LibraryService.fetchBooks()
            .then((data) => {
                this.setState({
                    books: data.data
                })
            });
    }

    //Todo: load categories
    loadCategories = () => {
        LibraryService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            });
    }


    componentDidMount() {
        this.loadBooks();
        this.loadCategories();
    }

    //todo: Navigate??
    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div className="container">
                        <Routes>
                            <Route path="/books" element={<Books books={this.state.books} />} />
                            <Route path="/categories" element={<Categories categories={this.state.categories} />} />
                            {/*// /!*<Books books = {this.state.books}/>*!/*/}

                        </Routes>
                        {/*<Navigate to="/books" replace={true} />*/}
                    </div>
                </main>
            </Router>
        );
    }
}

export default App;
