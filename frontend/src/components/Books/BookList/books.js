import React from "react";
import ProductTerm from "../BookTerm/productTerm";

//functional component

//parent component predava props
const books = (props) => {
    return (
      <div className={"container mm-4 mt-5"}>
          <div className={"row"}>
              <div className={"row"}>
                  <table className={"table table-striped"}>
                      <thead>
                        <tr>
                            <th scope={"col"}>Name</th>
                            <th scope={"col"}>Category</th>
                            <th scope={"col"}>Author</th>
                            <th scope={"col"}>Available Copies</th>
                        </tr>
                      </thead>
                      <tbody>
                      {props.books.map((term) =>{
                          return(
                            <ProductTerm term={term}/>
                          );
                          }
                      )}
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
    );
}

export default books;