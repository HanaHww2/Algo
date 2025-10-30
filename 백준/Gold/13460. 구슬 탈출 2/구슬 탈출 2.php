<?php
  
  $line = trim(fgets(STDIN));
  $di = explode(' ', $line);
  $R = (int) $di[0];
  $C = (int) $di[1];

  $board = [];
  $R_pos = [];
  $B_pos = [];
  $O_pos = [];

  $dir = [[1, 0], [-1, 0], [0, 1], [0, -1]];

  for ($r = 0; $r < $R; $r++) {
    $line = trim(fgets(STDIN));
    $chars = str_split($line);
    $board[] = $chars;

    for ($c = 0; $c < $C; $c++) {
      $char = $chars[$c];

      if ($char == 'R') {
        $R_pos = [$r, $c];
      } else if ($char == 'B') {
        $B_pos = [$r, $c];
      } else if ($char == 'O') {
        $O_pos = [$r, $c];
      }
    }
  }

  function bfs() {
    global $R_pos, $B_pos, $dir, $R, $C, $board;
    $q = new SplQueue();
    $q->enqueue([$R_pos, $B_pos, 0, -1]);

    while (!$q->isEmpty()) {
      $curr = $q->dequeue();

      if ($curr[2] > 9) return -1;

      foreach ($dir as $key => $direction) {

        if ($curr[3] != -1 and ($key == $curr[3] or (int) ($key / 2) == (int) ($curr[3] / 2))) continue;

        $bflag = false;
        $bcnt = 0;
        $nbr = $curr[1][0];
        $nbc = $curr[1][1];
        while (true) {
          $nbr += $direction[0];
          $nbc += $direction[1];
          
          if ($nbr < 0 or $nbr >= $R or $nbc < 0 or $nbc >= $C or $board[$nbr][$nbc] == '#') {
            $nbr -= $direction[0];
            $nbc -= $direction[1];
            break;
          }
          $bcnt++;
          if ($board[$nbr][$nbc] == 'O') {
            $bflag = true;
            break;
          }
        }
        if ($bflag) continue;
          
        $rflag = false;
        $rcnt = 0;
        $nrr = $curr[0][0];
        $nrc = $curr[0][1];
        while (true) {
          $nrr += $direction[0];
          $nrc += $direction[1];
          
          if ($nrr < 0 or $nrr >= $R or $nrc < 0 or $nrc >= $C or $board[$nrr][$nrc] == '#') {
            $nrr -= $direction[0];
            $nrc -= $direction[1];
            break;
          }

          $rcnt++;
          if ($board[$nrr][$nrc] == 'O') {
            $rflag = true;
            break;
          }
        }
        if ($rflag) return $curr[2] + 1; 

        if ($nrr == $nbr and $nrc == $nbc) {
          if ($rcnt > $bcnt) { $nrr -= $direction[0]; $nrc -= $direction[1]; } 
          else { $nbr -= $direction[0]; $nbc -= $direction[1]; }
        }

        $q -> enqueue([[$nrr, $nrc], [$nbr, $nbc], $curr[2] + 1, $key]);
      }

    }
    return -1;
  }

  echo bfs();