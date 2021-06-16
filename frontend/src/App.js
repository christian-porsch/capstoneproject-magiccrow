import LandingPage from "./pages/LandingPage";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import CardSearchPage from "./pages/CardSearchPage";
import CardCollectionPage from "./pages/CardCollectionPage";
import useCardSearch from "./hooks/useCardSearch";
import CardSearchResult from "./components/CardSearchResult";

function App() {

    const {cards, getSpecificCard} = useCardSearch();

  return (
      <Router>
        <Switch>
          <Route path={'/'} exact>
                <LandingPage />
          </Route>
          <Route path={'/searchCards'} exact>
                <CardSearchPage searchForCard={getSpecificCard}/>
                <CardSearchResult cards={cards}/>
          </Route>
          <Route path={'/myCollection'} exact>
                <CardCollectionPage />
          </Route>
        </Switch>
     </Router>
  );
}

export default App;
