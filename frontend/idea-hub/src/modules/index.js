import { combineReducers } from "redux";
import { all } from "redux-saga/effects";
import loading from "./loading";
import entryWrite, { entryWriteSaga } from "./entryWrite";

const rootReducer = combineReducers({
  loading,
  entryWrite,
});

export function* rootSaga() {
  yield all([entryWriteSaga()]);
}

export default rootReducer;
