import React, { useEffect, useCallback } from "react";
import EntryEditor from "../../components/entryWrite/EntryEditor";
import { useSelector, useDispatch } from "react-redux";
import {
  changeField,
  initialize,
  setInitialState,
} from "../../modules/entryWrite";

const EntryEditorContainer = () => {
  const dispatch = useDispatch();
  const {
    title,
    body,
    status,
    videoURL,
    team,
    taggedContest,
    taggedContestID,
    github,
  } = useSelector(({ entryWrite }) => ({
    title: entryWrite.title,
    body: entryWrite.body,
    taggedContest: entryWrite.taggedContest,
    taggedContestID: entryWrite.taggedContestID,
    videoURL: entryWrite.videoURL,
    team: entryWrite.team,
    status: entryWrite.status,
    github: entryWrite.github,
  }));
  const onChangeField = useCallback(
    (payload) => dispatch(changeField(payload)),
    [dispatch]
  );

  useEffect(() => {
    return () => {
      //unmount시 entryWrite와 관련된 상태를 초기화 하는데 사용
      dispatch(initialize());
    };
  }, [dispatch]);

  return (
    <EntryEditor
      onChangeField={onChangeField}
      title={title}
      body={body}
      taggedContest={taggedContest}
      taggedContestID={taggedContestID}
      videoURL={videoURL}
      team={team}
      status={status}
      github={github}
    />
  );
};

export default EntryEditorContainer;
