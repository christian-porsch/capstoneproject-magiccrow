import LandingPage from "./pages/LandingPage";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import CardSearchPage from "./pages/CardSearchPage";
import CardCollectionPage from "./pages/CardCollectionPage";
import SingleCardPage from "./pages/SingleCardPage";

function App() {


  return (
      <Router>
        <Switch>
          <Route path={'/'} exact>
                <LandingPage />
          </Route>
          <Route path={'/searchCards'} exact>
                <CardSearchPage />
          </Route>
          <Route path={'/myCollection'} exact>
                <CardCollectionPage />
          </Route>
            <Route path={'/:name/:id'} exact>
                <SingleCardPage />
            </Route>
        </Switch>
     </Router>
  );
}

export default App;
