import React from "react";
import styled, { css } from "styled-components";
import { Link } from "react-router-dom";
import palette from "../../lib/styles/palette";

const buttonStyle = css`
  border: none;
  font-family: "Montserrat", "Noto Sans KR", Sans-Serif;
  font-size: 16px;
  font-weigt: bold;
  padding: 0.5rem 1.5rem;
  margin: 0.2rem;
  color: ${palette.gray[7]};
  outline: none;
  cursor: pointer;

  &:disabled {
    background: ${palette.gray[3]};
    color: ${palette.gray[5]};
    cursor: not-allowed;
  }

  ${(props) =>
    props.fullWidth &&
    css`
      padding-top: 0.75rem;
      padding-bottom: 0.75rem;
      width: 100%;
      font-size: 1.125rem;
      margin-bottom: 0.2rem;
    `}

  ${(props) =>
    props.fullHeight &&
    css`
      height: 100%;
    `}

  ${(props) =>
    props.toDefaultColor &&
    css`
      background-color: #ff814b;
      &:hover {
        background-color: #eb713d;
      }
    `}

    ${(props) =>
    props.hasBorder &&
    css`
      border: 1px solid ${palette.gray[3]};
    `}

    ${(props) =>
    props.blackBackground &&
    css`
      background-color: black;
      color: white;
    `}
`;

const StyledButton = styled.button`
  ${buttonStyle}
`;

const StyledLink = styled(Link)`
  ${buttonStyle}
  display: flex;
`;

const Button = (props) => {
  return props.to ? <StyledLink {...props} /> : <StyledButton {...props} />;
};
export default Button;
