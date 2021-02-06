import './App.css';
import StudentList from "./component/studentList.component";
import { BrowserRouter as Router, Switch, Route} from "react-router-dom";
import StudentUpdate from "./component/studentUpdate.component";
import StudentCreate from "./component/studentCreate.component";
import React from "react";

function App() {
  return (
    <div className="App">
        <Router>
            <Switch>
                <Route exact
                       path={["/students", ""]}
                       component={StudentList}
                />
                <Route exact
                       path={"/update"}
                       component={StudentUpdate}
                />
                <Route exact
                       path={"/create"}
                       component={StudentCreate}
                />
            </Switch>
        </Router>
    </div>
  );
}

export default App;
