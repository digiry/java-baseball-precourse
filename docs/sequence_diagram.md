# 시퀸스 다이어그램

## Application.main()

GameBoard 를 초기화하고 게임을 실행한다.

```mermaid
sequenceDiagram
    actor Player
    participant Application
    participant GameView
    participant DefensePlayer
    participant GameController
    participant GameBoard
    
    Player --> Application: main()
    Application -->> GameView: new
    Application -->> DefensePlayer: new
    Application -->> GameController: new (GameView, DefensePlayer)
    Application -->> GameBoard: new (GameController)
    Application -->> GameBoard: Run()
```

## GameBoard.Run() - INIT

**Init 게임 상태**일 때 수행하는 과정을 설명합니다.

게임 상태 객체를 준비하고 초기 게임 상태로 상태를 설정하고 게임 동작을 반복한다.

✔️ Application 에서 `Run()` 요청한 부분은 생략합니다.

```mermaid
sequenceDiagram
    participant GameBoard
    participant InitState
    participant GameController
    participant DefensePlayer

    GameBoard -->> GameBoard: InitializeGameStates()
    GameBoard -->> GameBoard: SetState(InitState)

    loop state != ExitState
        GameBoard -->> InitState: ViewUpdate()

        GameBoard -->> InitState: ReadInput()
        InitState -->> GameController: GetEmptyInputData()
        GameController -->> InitState: empty

        GameBoard -->> InitState: EvaluatePlayerData(empty)
        InitState -->> GameController: EvaluatePlayerData(empty)
        GameController -->> DefensePlayer: MakeRandomNumbers()

        GameBoard -->> InitState: NextState()
        InitState -->> GameBoard: SetState(InputState)
    end
```

## GameBoard.Run() - INPUT

**Input 게임 상태**일 때 수행하는 과정을 설명합니다.

공격 게임 플레이에게 입력 메시지를 표시하고 숫자를 입력받는다. 받은 숫자를 수비 플레이어에게 전달하여 숫자 비교 판정을 수행합니다.

✔️ Application 에서 `Run()` 요청한 부분은 생략합니다.

```mermaid
sequenceDiagram
    participant GameBoard
    participant InputState
    participant GameController
    participant GameView
    participant DefensePlayer

    loop (state != ExitState)
        GameBoard -->> InputState: ViewUpdate()
        InputState -->> GameController: PrintInputRequestMessage()
        GameController -->> GameView: PrintInputRequestMessage()

        GameBoard -->>+ InputState: ReadInput()
        InputState -->>+ GameController: ReadInput()
        GameController -->>+ GameView: ReadNumbersInput()
        GameView -->> GameView: ValidateNumbersInput()
        alt Is Validation Error
            GameView -->> GameBoard: Throw IllegalArgumentException
        end
        GameView -->>- GameController: numbers
        GameController -->>- InputState: numbers
        InputState -->>- GameBoard: numbers

        GameBoard -->> InputState: EvaluatePlayerData(numbers)
        InputState -->> GameController: EvaluatePlayerData(numbers)
        GameController -->> DefensePlayer: EvaluateData(numbers)

        GameBoard -->> InputState: NextState()
        InputState -->> GameBoard: SetState(ResultState)
    end
```

## GameBoard.Run() - RESULT

**Result 게임 상태**일 때 수행하는 과정을 설명합니다.

수비 플레이어가 비교 판정한 결과를 화면에 표시합니다.

판정한 결과가 3 스트라이크이면 스테이지 완료 상태(DoneState)로 설정합니다.  
그 외 결과이면 다시 사용자 입력을 받는 입력 상태(InputState)로 설정합니다.

✔️ Application 에서 `Run()` 요청한 부분은 생략합니다.

```mermaid
sequenceDiagram
    participant GameBoard
    participant ResultState
    participant GameController
    participant GameView
    participant DefensePlayer

    loop (state != ExitState)
        GameBoard -->> ResultState: ViewUpdate()
        ResultState -->> GameController: PrintGameResult()
        GameController -->>+ DefensePlayer: GetBallCount()
        DefensePlayer -->>- GameController: ballCount 
        GameController -->>+ DefensePlayer: GetStrikeCount()
        DefensePlayer -->>- GameController: StrikeCount 
        GameController -->> GameView: PrintGameResult(ballCount, StrikeCount)

        GameBoard -->> ResultState: ReadInput()
        ResultState -->> GameController: GetEmptyInputData()
        GameController -->> ResultState: empty

        GameBoard -->> ResultState: EvaluatePlayerData()

        GameBoard -->> ResultState: NextState()
        ResultState -->> GameController: IsTripleStrike()
        GameController -->> DefensePlayer: IsTripleStrike()
        opt Yes Triple Strike
            DefensePlayer -->> GameController: yes
            GameController -->> ResultState: yes
            ResultState -->> GameBoard: SetState(DoneState)
        end

        alt No Others
            DefensePlayer -->> GameController: no
            GameController -->> ResultState: no
            ResultState -->> GameBoard: SetState(InputState)
        end
    end

```

## GameBoard.Run() - DONE

**Done 게임 상태**일 때 수행하는 과정을 설명합니다.

공격 플레이어가 3 스트라이크로 결과를 맞춰서 축하 메시지와 해당 스테이지를 종료하는 메시지를 화면에 표시합니다.

✔️ Application 에서 `Run()` 요청한 부분은 생략합니다.

```mermaid
sequenceDiagram
    participant GameBoard
    participant DoneState
    participant GameController
    participant GameView

    loop (state != ExitState)
        GameBoard -->> DoneState: ViewUpdate()
        DoneState -->> GameController: PrintStageDoneMessage()
        GameController -->> GameView: PrintStageDoneMessage()

        GameBoard -->> DoneState: ReadInput()
        DoneState -->> GameController: GetEmptyInputData()
        GameController -->> DoneState: empty
        DoneState -->> GameBoard: empty

        GameBoard -->> DoneState: EvaluatePlayerData(empty)

        GameBoard -->> DoneState: NextState()
        DoneState -->> GameBoard: SetState(MenuState)
    end
```

## GameBoard.Run() - MENU

**Menu 게임 상태**일 때 수행하는 과정을 설명합니다.

공격이 성공하여 스테이지가 끝나 재시작할 지 게임을 종료할 지 메시지를 화면에 표시합니다.

사용자 선택이 재시작이면 게임 초기 상태(InitState)로 설정합니다.  
다른 선택인 게임 종료라면 게임 닫는 상태(CloseState)로 설정합니다.

✔️ Application 에서 `Run()` 요청한 부분은 생략합니다.

```mermaid
sequenceDiagram
    participant GameBoard
    participant MenuState
    participant GameController
    participant GameView

    loop (state != ExitState)
        GameBoard -->> MenuState: ViewUpdate()
        MenuState -->> GameController: PrintMenuMessage()
        GameController -->> GameView: PrintMenuMessage()

        GameBoard -->>+ MenuState: ReadInput()
        MenuState -->>+ GameController: ReadMenuInputData()
        GameController -->>+ GameView: ReadMenuInputData()
        Note right of GameView: 사용자가 Menu 항목 1, 2 이외 값이 입력하면 재입력을 요청을 반복한다.
        GameView -->>- GameController: selectedMenu
        GameController -->>- MenuState: selectedMenu
        MenuState -->> MenuState: SetSelectedMenu(selectedMenu)
        MenuState -->>- GameBoard: selectedMenu

        GameBoard -->> MenuState: EvaluatePlayerData(selectedMenu)

        opt Choice is RETRY
            GameBoard -->> MenuState: NextState()
            MenuState -->> MenuState: GetSelectedMenu()
            MenuState -->> GameBoard: SetState(InitState)
        end

        alt Choice is CLOSE
            GameBoard -->> MenuState: NextState()
            MenuState -->> MenuState: GetSelectedMenu()
            MenuState -->> GameBoard: SetState(CloseState)
        end
    end
```

## GameBoard.Run() - CLOSE

**Close 게임 상태**일 때 수행하는 과정을 설명합니다.

사용자가 진행 여부 메뉴에서 게임 종료를 선택하여 게임 종료 메시지를 화면에 표시합니다.

게임 흐름을 종료하려고 게임 종료 상태(ExitState)로 설정합니다.

✔️ Application 에서 `Run()` 요청한 부분은 생략합니다.

```mermaid
sequenceDiagram
    participant GameBoard
    participant CloseState
    participant GameController
    participant GameView

    loop (state != ExitState)
        GameBoard -->> CloseState: ViewUpdate()
        CloseState -->> GameController: PrintCloseMessage()
        GameController -->> GameView: PrintCloseMessage()

        GameBoard -->> CloseState: ReadInput()
        CloseState -->> GameController: GetEmptyInputData()
        GameController -->> CloseState: empty
        CloseState -->> GameBoard: empty

        GameBoard -->> CloseState: EvaluatePlayerData(empty)

        GameBoard -->> CloseState: NextState()
        CloseState -->> GameBoard: SetState(ExitState)
    end
```
