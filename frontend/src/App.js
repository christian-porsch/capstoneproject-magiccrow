import LandingPage from "./pages/LandingPage";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import CardSearchResult from "./pages/CardSearchResult";

function App() {
  return (
      <Router>
        <Switch>
          <Route path={'/'} exact>
                <LandingPage />
          </Route>
          <Route path={'/searchCards'} exact>
                <CardSearchResult />
          </Route>
        </Switch>
     </Router>
  );
}

export default App;
