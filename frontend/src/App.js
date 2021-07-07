import LandingPage from "./pages/LandingPage";
import {BrowserRouter as Router, Switch, Route} from "react-router-dom";
import CardSearchPage from "./pages/CardSearchPage";
import CardCollectionPage from "./pages/CardCollectionPage";
import SingleCardPage from "./pages/SingleCardPage";
import MagicCrowHeader from "./components/MagicCrowHeader";
import MagicCrowFooter from "./components/MagicCrowFooter";
import SingleCardInCollectionPage from "./pages/SingleCardInCollectionPage";

function App() {


    return (

        <Router>
            <MagicCrowHeader/>
            <Switch>
                <Route path={'/'} exact>
                    <LandingPage/>
                </Route>
                <Route path={'/searchCards'} exact>
                    <CardSearchPage/>
                </Route>
                <Route path={'/myCollection'} exact>
                    <CardCollectionPage/>
                </Route>
                <Route path={'/:id'} exact>
                    <SingleCardPage/>
                </Route>
                <Route path={'/myCollection/:id'} exact>
                    <SingleCardInCollectionPage/>
                </Route>
            </Switch>
            <MagicCrowFooter/>
        </Router>

    );
}

export default App;
