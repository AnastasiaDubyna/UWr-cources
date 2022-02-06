import unittest
import ex4_sheet2


class MyTestCase(unittest.TestCase):
    def setUp(self):
        self.compressed_line_one = [(1, 'P'), (2, 'p'), (3, 'o'), (1, 'd'), (1, 'z'), (1, 'i'), (1, 'a'), (1, 'ł'),
                                    (1, ' '), (1, 'p'), (1, 'e'), (4, 'r'), (1, 'y'), (1, 'k'), (1, 'l'), (1, 'i'),
                                    (1, 'n'), (2, 'a'), (1, 'l'), (1, 'n'), (1, 'y'), (1, ' '), (1, 'i'), (1, 'n'),
                                    (1, 'i'), (1, 'c'), (3, 'j'), (1, 'a'), (1, 'ł'), (1, 'ó'), (1, 'w'), (1, '!')]
        self.decompressed_line_one = "Pppooodział perrrryklinaalny inicjjjałów!"
        self.compressed_line_two = [(1, 'M'), (1, 'r'), (1, '.'), (1, ' '), (1, 'a'), (1, 'n'), (1, 'd'), (1, ' '),
                                    (1, 'M'), (1, 'r'), (1, 's'), (1, '.'), (1, ' '), (1, 'D'), (1, 'u'), (1, 'r'),
                                    (1, 's'), (1, 'l'), (1, 'e'), (1, 'y'), (1, ','), (1, ' '), (1, 'o'), (1, 'f'),
                                    (1, ' '), (1, 'n'), (1, 'u'), (1, 'm'), (1, 'b'), (1, 'e'), (1, 'r'), (1, ' '),
                                    (1, 'f'), (1, 'o'), (1, 'u'), (1, 'r'), (1, ','), (1, ' '), (1, 'P'), (1, 'r'),
                                    (1, 'i'), (1, 'v'), (1, 'e'), (1, 't'), (1, ' '), (1, 'D'), (1, 'r'), (1, 'i'),
                                    (1, 'v'), (1, 'e'), (1, ','), (1, ' '), (1, 'w'), (1, 'e'), (1, 'r'), (1, 'e'),
                                    (1, ' '), (1, 'p'), (1, 'r'), (1, 'o'), (1, 'u'), (1, 'd'), (1, ' '), (1, 't'),
                                    (1, 'o'), (1, ' '), (1, 's'), (1, 'a'), (1, 'y'), (1, ' '), (1, 't'), (1, 'h'),
                                    (1, 'a'), (1, 't'), (1, ' '), (1, 't'), (1, 'h'), (1, 'e'), (1, 'y'), (1, ' '),
                                    (1, 'w'), (1, 'e'), (1, 'r'), (1, 'e'), (1, ' '), (1, 'p'), (1, 'e'), (1, 'r'),
                                    (1, 'f'), (1, 'e'), (1, 'c'), (1, 't'), (1, 'l'), (1, 'y'), (1, ' '), (1, 'n'),
                                    (1, 'o'), (1, 'r'), (1, 'm'), (1, 'a'), (1, 'l')]
        self.decompressed_line_two = "Mr. and Mrs. Dursley, of number four, Privet Drive, were proud to say that " \
                                     "they were perfectly normal"

    def test_compression(self):
        self.assertEqual(ex4_sheet2.do_compression(self.decompressed_line_one), self.compressed_line_one)
        self.assertEqual(ex4_sheet2.do_compression(self.decompressed_line_two), self.compressed_line_two)

    def test_decompression(self):
        self.assertEqual(ex4_sheet2.do_decompression(self.compressed_line_one), self.decompressed_line_one)
        self.assertEqual(ex4_sheet2.do_decompression(self.compressed_line_two), self.decompressed_line_two)

    def test_words_compression(self):
        self.assertEqual(ex4_sheet2.compress("Privet Drive"), [(1, 'P'), (1, 'r'), (1, 'i'), (1, 'v'), (1, 'e'),
                                                               (1, 't'), (1, ' '), (1, 'D'), (1, 'r'), (1, 'i'),
                                                               (1, 'v'), (1, 'e'), (1, ' ')])
        self.assertEqual(ex4_sheet2.compress("      "), [(6, ' '), (1, ' ')])
        self.assertEqual(ex4_sheet2.compress("Privet DriV   e"), [(1, 'P'), (1, 'r'), (1, 'i'), (1, 'v'), (1, 'e'),
                                                                  (1, 't'), (1, ' '), (1, 'D'), (1, 'r'), (1, 'i'),
                                                                  (1, 'V'), (3, ' '), (1, 'e'), (1, ' ')])


if __name__ == '__main__':
    unittest.main()
