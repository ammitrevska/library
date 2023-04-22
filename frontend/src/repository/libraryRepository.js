import axios from "../custom-axios/axios";

const LibraryService = {
    fetchBooks: () => {
        return axios.get("/books");
    },
    fetchCategories: () => {
        return axios.get("/categories");
    },
    fetchAuthors: () => {
        return axios.get("/author");
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);  // /books/delete/2
    },
    addBook: (name,category,author,availableCopies) => {
        return axios.post("/books/add",{
            //objet koj kje se predava - dto
                "name" : name,
                "category" : category,
                "author" : author,
                "availableCopies" : availableCopies
        });
    },
    editBook: (id,name,category,author,availableCopies ) => {
        return axios.put(`/books/edit/${id}`, {
            "name" : name,
            "category" : category,
            "author" : author,
            "availableCopies" : availableCopies
        });
    },
    //tekoven produkt
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    },
    markAsTakenBook: (id) => {
        return axios.post(`/books/taken/${id}`);
    }

}

export default LibraryService;