import { createAction, handleActions } from "redux-actions";
import createRequestSaga, {
  createRequestActionTypes,
} from "../lib/createRequestSaga";
import * as entryPostsAPI from "../lib/api/entryPosts";
import { takeLatest } from "redux-saga/effects";

//action type
const SET_INITIAL_STATE = "entrywrite/SET_INITIAL_STATE"; //taggedContest를 현재 contestName으로 초기화
const INITIALIZE = "entrywrite/INITIALIZE"; //모든 내용 초기화
const CHANGE_FIELD = "entrywrite/CHANGE_FIELD"; //특정 key값 바꾸기
const [ENTRY_WRITE_POST, ENTRY_WRITE_POST_SUCCESS, ENTRY_WRITE_POST_FAILURE] =
  createRequestActionTypes("entrywrite/ENTRY_WRITE_POST");
const SET_ORIGINAL_ENTRY = "entrywrite/SET_ORIGINAL_ENTRY"; //콘텐츠 수정버튼 클릭 시 기존 정보를 상태에 넣기 위함
const [UPDATE_ENTRY, UPDATE_ENTRY_SUCCESS, UPDATE_ENTRY_FAILURE] =
  createRequestActionTypes("entrywrite/UPDATE_ENTRY"); //포스트 수정

//action creating functions
export const setInitialState = createAction(SET_INITIAL_STATE);
export const initialize = createAction(INITIALIZE);
export const changeField = createAction(CHANGE_FIELD, ({ key, value }) => ({
  key,
  value,
}));
export const entryWritePost = createAction(
  ENTRY_WRITE_POST,
  ({
    title,
    body,
    taggedContest,
    taggedContestID,
    videoURL,
    team,
    status,
    github,
  }) => ({
    title,
    body,
    taggedContest,
    taggedContestID,
    videoURL,
    team,
    status,
    github,
  })
);
export const setOriginalEntry = createAction(
  SET_ORIGINAL_ENTRY,
  (entry) => entry
);
export const updateEntry = createAction(
  UPDATE_ENTRY,
  ({
    id,
    title,
    body,
    taggedContest,
    taggedContestID,
    videoURL,
    team,
    status,
    github,
  }) => ({
    id,
    title,
    body,
    taggedContest,
    taggedContestID,
    videoURL,
    team,
    status,
    github,
  })
);

//create saga
const entryWritePostSaga = createRequestSaga(
  ENTRY_WRITE_POST,
  entryPostsAPI.entryWritePost
);
const updateEntrySaga = createRequestSaga(
  UPDATE_ENTRY,
  entryPostsAPI.updateEntry
);
export function* entryWriteSaga() {
  yield takeLatest(ENTRY_WRITE_POST, entryWritePostSaga);
  yield takeLatest(UPDATE_ENTRY, updateEntrySaga);
}

//initial state
const initialState = {
  title: "",
  body: "",
  taggedContest: "",
  taggedContestID: "",
  videoURL: "",
  team: "",
  status: "",
  github: "",
  entry: null,
  entryError: null,
  originalEntryId: null,
};

//reducer
const entryWrite = handleActions(
  {
    [SET_INITIAL_STATE]: (state, { payload: contestName }) => ({
      ...initialState,
      taggedContest: contestName.contestName,
      taggedContestID: contestName.contestID,
    }),
    [INITIALIZE]: (state) => initialState,
    [CHANGE_FIELD]: (state, { payload: { key, value } }) => ({
      ...state,
      [key]: value,
    }),
    [ENTRY_WRITE_POST]: (state) => ({
      ...state,
      entry: null,
      entryError: null,
    }),
    [ENTRY_WRITE_POST_SUCCESS]: (state, { payload: entry }) => ({
      ...state,
      entry,
    }),
    [ENTRY_WRITE_POST_FAILURE]: (state, { payload: entryError }) => ({
      ...state,
      entryError,
    }),
    [SET_ORIGINAL_ENTRY]: (state, { payload: entry }) => ({
      ...state,
      title: entry.title,
      body: entry.body,
      taggedContest: entry.taggedContest,
      taggedContestID: entry.taggedContestID,
      videoURL: entry.videoURL,
      team: entry.team,
      status: entry.status,
      github: entry.github,
      stars: entry.stars,
      originalEntryId: entry._id,
    }),
    [UPDATE_ENTRY_SUCCESS]: (state, { payload: entry }) => ({
      ...state,
      entry,
    }),
    [UPDATE_ENTRY_FAILURE]: (state, { payload: entryError }) => ({
      ...state,
      entryError,
    }),
  },
  initialState
);

export default entryWrite;
