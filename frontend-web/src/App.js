import { Component } from "react";
import { BrowserRouter } from "react-router-dom";
import NavBar from "./components/NavBar";

class App extends Component {
  constructor(props) {
    super(props)
  }

  render(){
    return (
      <BrowserRouter>
        <div>
          <NavBar />
        </div>
      </BrowserRouter>
    );
  }

}

export default App;
