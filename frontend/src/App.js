import LandingPage from "./pages/LandingPage";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import CardSearchResult from "./pages/CardSearchResult";
import CardCollectionPage from "./pages/CardCollectionPage";

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
          <Route path={'/myCollection'} exact>
                <CardCollectionPage />
          </Route>
        </Switch>
     </Router>
  );
}

export default App;
