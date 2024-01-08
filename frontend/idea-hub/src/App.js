import { Route } from "react-router-dom";
import EntryWritePage from "./pages/EntryWritePage";
import MainPage from "./pages/MainPage";

const App = () => {
  return (
    <>
      <Route component={MainPage} exact path="/" />
      <Route component={EntryWritePage} path="/entrywrite" />
    </>
  );
};

export default App;
