import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: const GradeScreen(),
    );
  }
}

class GradeScreen extends StatelessWidget {
  const GradeScreen({super.key});

  String _getRank(double score) {
    if (score < 0 || score > 10) {
      return 'Không hợp lệ';
    } else if (score >= 8.0) {
      return 'Giỏi';
    } else if (score >= 6.5) {
      return 'Khá';
    } else if (score >= 5.0) {
      return 'Trung bình';
    } else {
      return 'Yếu';
    }
  }

  Color _getRankColor(String rank) {
    if (rank == 'Giỏi') {
      return Colors.green;
    } else if (rank == 'Khá') {
      return Colors.blue;
    } else if (rank == 'Trung bình') {
      return Colors.orange;
    } else if (rank == 'Yếu') {
      return Colors.red;
    } else {
      return Colors.grey;
    }
  }

  void _showScoreDetails(BuildContext context, double score, String rank) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text('Chi tiết điểm: $score'),
          content: Text('Xếp loại: $rank'),
          actions: [
            TextButton(
              onPressed: () {
                Navigator.of(context).pop();
              },
              child: const Text('Đóng'),
            ),
          ],
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    double mainScore = 8.5;
    String mainRank = _getRank(mainScore);

    List<double> testScores = [9, 7, 5.5, 3, 11];

    List<Widget> checkItems = [];
    for (var score in testScores) {
      String rank = _getRank(score);
      Color rankColor = _getRankColor(rank);

      checkItems.add(
        GestureDetector(
          onTap: () => _showScoreDetails(context, score, rank),
          child: Container(
            margin: const EdgeInsets.only(bottom: 8),
            padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 12),
            decoration: BoxDecoration(
              color: Colors.white,
              borderRadius: BorderRadius.circular(8),
              border: Border.all(color: Colors.grey.shade300),
            ),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Text('Điểm $score', style: const TextStyle(fontSize: 15)),
                Container(
                  padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 4),
                  decoration: BoxDecoration(
                    color: rankColor.withOpacity(0.1),
                    borderRadius: BorderRadius.circular(4),
                  ),
                  child: Text(
                    rank,
                    style: TextStyle(
                      color: rankColor,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
              ],
            ),
          ),
        ),
      );
    }

    return Scaffold(
      appBar: AppBar(
        backgroundColor: const Color(0xFF1E3A8A),
        title: const Text('Xếp loại học lực', style: TextStyle(color: Colors.white)),
      ),
      body: Container(
        color: Colors.grey.shade100,
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Container(
                width: double.infinity,
                padding: const EdgeInsets.all(24),
                decoration: BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.circular(12),
                  border: Border.all(color: Colors.grey.shade300),
                ),
                child: Column(
                  children: [
                    const Text('Điểm trung bình', style: TextStyle(color: Colors.grey)),
                    const SizedBox(height: 8),
                    Text(
                      '$mainScore',
                      style: const TextStyle(fontSize: 40, fontWeight: FontWeight.bold),
                    ),
                    const SizedBox(height: 16),
                    Container(
                      width: double.infinity,
                      padding: const EdgeInsets.symmetric(vertical: 12),
                      decoration: BoxDecoration(
                        color: _getRankColor(mainRank).withOpacity(0.1),
                        borderRadius: BorderRadius.circular(8),
                      ),
                      child: Text(
                        mainRank,
                        textAlign: TextAlign.center,
                        style: TextStyle(
                          color: _getRankColor(mainRank),
                          fontSize: 18,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                    ),
                  ],
                ),
              ),
              const SizedBox(height: 24),
              const Text(
                'KIỂM TRA THÊM',
                style: TextStyle(
                  color: Colors.grey,
                  fontWeight: FontWeight.bold,
                  fontSize: 13,
                ),
              ),
              const SizedBox(height: 8),
              Expanded(
                child: SingleChildScrollView(
                  child: Column(
                    children: checkItems,
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}