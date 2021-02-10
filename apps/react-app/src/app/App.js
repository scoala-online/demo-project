import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

import './App.css';

import StudentList from './components/studentList.component';
import StudentUpdate from './components/studentUpdate.component';
import StudentCreate from './components/studentCreate.component';

function App() {
  return (
    <div className="App">
      <Router>
        <Switch>
          <Route exact path={['/students', '']} component={StudentList} />
          <Route exact path={'/update'} component={StudentUpdate} />
          <Route exact path={'/create'} component={StudentCreate} />
        </Switch>
      </Router>
    </div>
  );
}

export default App;
